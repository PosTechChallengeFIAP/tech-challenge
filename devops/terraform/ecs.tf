resource "aws_ecs_cluster" "ecs_cluster" {
  name = "ecs-cluster"
}

resource "aws_instance" "ecs_instance" {
  ami                         = "ami-09245d5773578a1d6"
  instance_type               = "t2.micro"
  subnet_id                   = data.terraform_remote_state.network.outputs.public_subnet_id
  vpc_security_group_ids      = [data.terraform_remote_state.network.outputs.security_group_id]
  associate_public_ip_address = true
  iam_instance_profile        = "LabRole"

  user_data = <<-EOF
              #!/bin/bash
              echo "ECS_CLUSTER=${aws_ecs_cluster.ecs_cluster.name}" >> /etc/ecs/ecs.config
  EOF

  tags = {
    Name = "tech-challenge-ecs-instance"
  }
}

resource "aws_ecs_task_definition" "app_task" {
  family                   = "ecs-task"
  network_mode             = "bridge"
  requires_compatibilities = ["EC2"]
  container_definitions = jsonencode([
    {
      name      = "tech-challenge-app"
      image     = "loadinggreg/tech-challenge:latest"
      cpu       = 256
      memory    = 512
      essential = true
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 80
        }
      ]
    }
  ])
}

resource "aws_ecs_service" "app_service" {
  name            = "tech-challenge-ecs-service"
  cluster         = aws_ecs_cluster.ecs_cluster.id
  task_definition = aws_ecs_task_definition.app_task.arn
  desired_count   = 1
  launch_type     = "EC2"
}
