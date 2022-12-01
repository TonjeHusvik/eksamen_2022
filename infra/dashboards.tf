resource "aws_cloudwatch_dashboard" "master" {
  dashboard_name = var.candidate_id
  dashboard_body = <<DASHBOARD
{
  "widgets": [
    {
      "type": "metric",
      "x": 0,
      "y": 0,
      "width": 12,
      "height": 6,
      "properties": {
        "metrics": [
          [
            "${var.candidate_id}",
            "carts.value"
          ]
        ],
        "period": 10,
        "stat": "Maximum",
        "region": "eu-west-1",
        "title": "Total number of carts"
      }
    },
    {
      "type": "metric",
      "x": 0,
      "y": 0,
      "width": 12,
      "height": 6,
      "properties": {
        "metrics": [
          [
            "${var.candidate_id}",
            "cartsvalue.value"
          ]
        ],
        "period": 10,
        "stat": "Maximum",
        "region": "eu-west-1",
        "title": "Sum of price"
      }
    },
    {
      "type": "metric",
      "x": 0,
      "y": 0,
      "width": 12,
      "height": 6,
      "properties": {
        "metrics": [
          [
            "${var.candidate_id}",
            "checkout.count"
          ]
        ],
        "period": 10,
        "stat": "Maximum",
        "region": "eu-west-1",
        "title": "Total checkouts"
      }
    },
    {
      "type": "metric",
      "x": 5,
      "y": 5,
      "width": 12,
      "height": 6,
      "properties": {
        "metrics": [
          [
            "${var.candidate_id}",
            "checkout_latency.avg"
          ]
        ],
        "period": 10,
        "stat": "Average",
        "region": "eu-west-1",
        "title": "Average response time in checkout"
      }
    }
  ]
}
DASHBOARD
}