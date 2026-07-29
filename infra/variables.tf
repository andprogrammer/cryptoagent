variable "subscription_id" {}

variable "location" {
  default = "swedencentral"
}

variable "resource_group_name" {
  default = "crypto-agent-rg"
}

variable "openrouter_api_key" {
  sensitive = true
}