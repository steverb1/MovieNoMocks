### Building and running your application

When you're ready, start your application by running:
`docker compose up --build`.

Your application will be available at http://localhost:5001.

### Deploying your application to the cloud

First, build your image, e.g.: `docker build -t myapp .`.
If your cloud uses a different CPU architecture than your development
machine (e.g., you are on a Mac M1 and your cloud provider is amd64),
you'll want to build the image for that platform, e.g.:
`docker build --platform=linux/amd64 -t myapp .`.

Then, push it to your registry, e.g. `docker push myregistry.com/myapp`.

Consult Docker's [getting started](https://docs.docker.com/go/get-started-sharing/)
docs for more detail on building and pushing.

I used this description to help set things up:
https://salithachathuranga94.medium.com/deploy-rest-api-using-spring-boot-mongodb-and-docker-e7ab620b24d6

To build and deploy:
docker compose down (if already up)
mvn clean package
docker compose build
docker compose up -d

Postman tips:
POST:
http://localhost:5001/api/movies
Body:
{
"title": "The Hitch Hiker's Guide to the Galaxy",
"year": 1981
}

GET:
http://localhost:5001/api/movies?title=The Hitch Hiker's Guide to the Galaxy&year=1981
