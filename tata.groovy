pipeline {
    agent none

    parameters {
      stringParam("CI_MESSAGE", "", "Contents of the CI message received from UMB.")
    }

    stages {
        stage("test") {
          steps {
            echo "${params.CI_MESSAGE}"
          }
        }
    }
}
