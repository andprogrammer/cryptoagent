resource "random_string" "suffix" {
  length  = 5
  upper   = false
  numeric = true
  special = false
}

resource "azurerm_resource_group" "rg" {

  name     = var.resource_group_name
  location = var.location
}

resource "azurerm_container_registry" "acr" {

  name                = "cryptoagent${random_string.suffix.result}"
  resource_group_name = azurerm_resource_group.rg.name
  location            = azurerm_resource_group.rg.location

  sku           = "Basic"
  admin_enabled = true
}

resource "azurerm_log_analytics_workspace" "logs" {

  name                = "crypto-agent-logs"
  location            = var.location
  resource_group_name = azurerm_resource_group.rg.name

  sku               = "PerGB2018"
  retention_in_days = 30
}

resource "azurerm_container_app_environment" "env" {

  name                       = "crypto-agent-env"
  location                   = var.location
  resource_group_name        = azurerm_resource_group.rg.name
  log_analytics_workspace_id = azurerm_log_analytics_workspace.logs.id
}

resource "azurerm_container_app" "app" {

  name                         = "crypto-agent"
  resource_group_name          = azurerm_resource_group.rg.name
  container_app_environment_id = azurerm_container_app_environment.env.id

  revision_mode = "Single"

  secret {
    name  = "acr-password"
    value = azurerm_container_registry.acr.admin_password
  }

  secret {
    name  = "openrouter-key"
    value = var.openrouter_api_key
  }

  registry {

    server               = azurerm_container_registry.acr.login_server
    username             = azurerm_container_registry.acr.admin_username
    password_secret_name = "acr-password"
  }

  template {

    min_replicas = 0
    max_replicas = 1

    container {

      name   = "crypto-agent"
      image  = "${azurerm_container_registry.acr.login_server}/crypto-agent:latest"
      cpu    = 0.5
      memory = "1Gi"

      env {
        name = "OPENROUTER_API_KEY"

        secret_name = "openrouter-key"
      }
    }
  }

  ingress {

    external_enabled = true

    target_port = 8080

    traffic_weight {
      percentage = 100
      latest_revision = true
    }
  }
}