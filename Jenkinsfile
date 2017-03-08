stage 'Checkout'
node {
   stage 'Docker Builds'
   docker.withRegistry('', 'private-login') {
        parallel(
            "Build Worker App":{def myEnv = docker.build('mekenthompson/example-voting-app-worker:latest', 'worker').push('latest')},
            "Build Result App":{def myEnv = docker.build('mekenthompson/example-voting-app-result:latest', 'result').push('latest')},
            "Build Vote App":{def myEnv = docker.build('mekenthompson/example-voting-app-vote:latest', 'vote').push('latest')}
            )
    }
    stage 'Kubernetes Deployment'
    sh 'kubectl apply -f kubernetes/basic-full-deployment.yml'
    sh 'kubectl delete pods -l app=vote'
    sh 'kubectl delete pods -l app=result'
    stage 'Smoke Test'
    sh 'kubectl get deployments'
}