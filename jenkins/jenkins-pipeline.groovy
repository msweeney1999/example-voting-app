stage 'Checkout'
node {
    git 'https://github.com/mekenthompson/example-voting-app.git' // Checks out example votiung app repository
   stage 'Docker Builds'
   docker.withRegistry('', 'docker-login') {
        parallel(
            "Build Worker App":{def myEnv = docker.build('mekenthompson/example-voting-app-worker', 'worker').push('latest')},
            "Build Result App":{def myEnv = docker.build('mekenthompson/example-voting-app-result', 'result').push('latest')},
            "Build Vote App":{def myEnv = docker.build('mekenthompson/example-voting-app-vote', 'vote').push('latest')}
            )
    }
    stage 'Kubernetes Deployment'
    stage 'Smoke Test'
}