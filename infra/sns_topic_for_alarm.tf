resource "aws_cloudwatch_metric_alarm" "zerosum" {
  alarm_name                = "cart-over-5"
  namespace                 = "tohu008"
  metric_name               = "cart_count.value"
  comparison_operator       = "GreaterThanThreshold"
  threshold                 = "5"
  evaluation_periods        = "5"
  period                    = "300"
  statistic                 = "Maximum"
  alarm_description         = "This alarm goes off as soon as the total amount of carts exceeds 5 over 5 minutes"
  insufficient_data_actions = []
  alarm_actions       = [aws_sns_topic.alarms.arn]
}

resource "aws_sns_topic" "alarms" {
  name = "alarm-topic-${var.candidate_id}"
}

resource "aws_sns_topic_subscription" "user_updates_sqs_target" {
  topic_arn = aws_sns_topic.alarms.arn
  protocol  = "email"
  endpoint  = var.candidate_email
}