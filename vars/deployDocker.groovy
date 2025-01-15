def call() {
    stage('Deploy Docker') {
        def appName = "${env.NEXUS_ARTIFACT_ID}"
        def artifactVersion = "${env.ARTIFACT_VERS}-${env.DEPLOY_TAG}"
        def stableImageTag = "${env.NEXUS_URL_DOCKER}/docker-releases/${appName}:1.15-20250115074808-release"
        def newImageTag = "${env.NEXUS_URL_DOCKER}/docker-releases/${appName}:${artifactVersion}"
        def logDir = "/var/log/${appName}"

        try {
            // Pull both images
            sh "docker pull ${stableImageTag} || echo 'No stable image found. Skipping pull for stable.'"
            sh "docker pull ${newImageTag}"

            // Stop and remove any existing container
            sh """
                docker stop ${appName} || true
                docker rm ${appName} || true
            """

            // Run the new image
            sh """
                docker run -d --name ${appName} \
                -p 8080:8080 \
                -v ${logDir}:/app/logs \
                ${newImageTag}
            """

            // Health check
            healthCheck()

        } catch (Exception e) {
            echo "Deployment of new image failed. Rolling back to stable image."

            // Rollback to stable image
            sh """
                docker stop ${appName} || true
                docker rm ${appName} || true
                docker run -d --name ${appName} \
                -p 8080:8080 \
                -v ${logDir}:/app/logs \
                ${stableImageTag}
            """
        }
    }
}
