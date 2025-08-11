pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'test-output/ExtentReports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])
            }
        }

        stage('Archive Extent Report') {
            steps {
                archiveArtifacts artifacts: 'test-output/ExtentReports/ExtentReport.html', fingerprint: true
            }
        }

    }

    post {
        always {
            echo 'Pipeline finished. Reports published.'
        }
    }
}
