pipeline {

    agent none

    parameters {
        string(
            name: 'RHEL_COMPOSE_ID',
            description: 'RHEL Compose Id to test',
            defaultValue: 'TEST'
        )
    }

    options {
        timestamps()
        buildDiscarder(logRotator(daysToKeepStr: '30'))
    }

    stages {

        stage ('Pipeline Information') {
            steps {
                echo "Compose ID: ${params.RHEL_COMPOSE_ID}"
                sh "env"
            }
        }

    }

}
