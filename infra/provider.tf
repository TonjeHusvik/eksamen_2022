terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.40.0"
    }
  }
  backend "s3" {
    bucket = "1013-exam"
    key    = "1013-exam.state"
    region = "eu-north-1"
  }
}