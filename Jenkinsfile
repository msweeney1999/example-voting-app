stage 'Checkout'
node {
   git 'https://github.com/mekenthompson/example-voting-app.git' // Checks out example votiung app repository
   stage 'Docker Hub Login'
   withCredentials([string(credentialsId: 'dockersecret', variable: 'USERPASS')]) {
    sh '''
      set +x
      docker login -u mekenthompson -p $USERPASS 
    '''
   }
   stage 'Azure Container Registry Login'
   withCredentials([string(credentialsId: 'acrsecret', variable: 'USERPASS')]) {
    sh '''
      set +x
      docker login -u f75c2xymvqv54 -p $USERPASS https://f75c2xymvqv54.azurecr.io
    '''
   }
   stage 'Docker Builds'
   docker.withRegistry('https://f75c2xymvqv54.azurecr.io/', 'private-login') {
        parallel(
            "Build Worker App":{def myEnv = docker.build('f75c2xymvqv54.azurecr.io/votingapp/example-voting-app-worker:latest', 'worker').push('latest')},
            "Build Result App":{def myEnv = docker.build('f75c2xymvqv54.azurecr.io/votingapp/example-voting-app-result:latest', 'result').push('latest')},
            "Build Vote App":{def myEnv = docker.build('f75c2xymvqv54.azurecr.io/votingapp/example-voting-app-vote:latest', 'vote').push('latest')}
            )
    }
    stage 'Smoke Tests'
    sh 'echo Smokkkeeeyyy'
}