def call() {
        script {
            try {
                sleep(20)   
                def response = httpRequest url: 'http://192.168.64.35:8080'
                if (response.status != 200) {
                    error("Health Check Failed with HTTP Status: ${response.status}")
                }
            } catch (e) {
                echo "Health Check Exception: ${e.getMessage()}"
                error("Health Check Failed")
            }
        }
    
}
