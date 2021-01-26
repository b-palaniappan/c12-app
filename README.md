# Springboot App
* Springboot REST api application
* MongoDB repository
* Uses JIB and Skaffold for continues development with the local Docker and Kubernetes.
* Spock and Springboot testing for Unit testing and Integration testing.
* Use Testcontainer to spin up MongoDB container for integration testing.
* OpenAPI V3 is available at `/v3/api-docs` url
* Swagger UI is available at `/swagger-ui.html`

## Run application in local using Skaffold
* Start mongoDB in local kubernetes using `k8s/mongo-deployment.yaml` config. Run mongodb in k8s using `kubectl apply -f mongo-deployment.yaml`
* To start application using skaffold run `skaffold dev`
