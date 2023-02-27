def Build(){
    echo "Building the application..."
    withCredentials([usernamePassword(credentialsId: 'Docker', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        //sh 'docker login -u $DOCKER_USER -p $DOCKER_PASS'
        sh "docker build -t mywebsite:1.0 ."
        sh "docker tag mywebsite:1.0 jatin1008/mywebsite:1.0"
        /* if you want to push to dockerhub you need to build image with your repo name and tag.
        otherwise you"ll have to tag image manually like i did above.*/
        sh "docker push jatin1008/mywebsite:1.0"
    }
}

def Test(){
    echo "Testing the application..."
}

def Deploy(){
    echo "Deploying the application..."
    def dockerCmd = "docker run -d -p 80:80 jatin1008/mywebsite:1.0"
    
        sh "${dockerCmd}"
    
}

return this