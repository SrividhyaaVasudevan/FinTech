pipeline {
    agent any

    stages {

        stage('Print Params') {
            steps {
                echo "Browser: ${params.BROWSER}"
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test -Dbrowser=${BROWSER}'
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
