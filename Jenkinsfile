stage 'Checkout'
node {
    git 'https://github.com/mekenthompson/example-voting-app.git' // Checks out example votiung app repository
   stage 'Docker Builds'
   docker.withRegistry('https://ignite-microsoft.azurecr.io', 'private-login') {
        parallel(
            "Build Worker App":{def myEnv = docker.build('ignite-microsoft.azurecr.io/example-voting-app-worker:latest', 'worker').push('latest')},
            "Build Result App":{def myEnv = docker.build('ignite-microsoft.azurecr.io/example-voting-app-result:latest', 'result').push('latest')},
            "Build Vote App":{def myEnv = docker.build('ignite-microsoft.azurecr.io/example-voting-app-vote:latest', 'vote').push('latest')}
            )
    }
    stage 'Kubernetes Deployment'
    sh 'kubectl apply -f kubernetes/basic-full-deployment.yml'
    sh 'kubectl delete pods -l app=vote'
    sh 'kubectl delete pods -l app=result'
    sh 'kubectl delete pods -l app=worker'
    sh 'kubectl delete pods -l app=db'
    sh 'kubectl delete pods -l app=redis'
    stage 'Smoke Test'
    sh 'kubectl get deployments'
}