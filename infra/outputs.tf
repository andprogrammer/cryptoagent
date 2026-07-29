output "container_registry" {

  value = azurerm_container_registry.acr.login_server
}

output "container_app_url" {

  value = azurerm_container_app.app.latest_revision_fqdn
}