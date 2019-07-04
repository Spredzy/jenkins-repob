pipeline {
    agent none

    parameters {
      string(
        name: "CI_MESSAGE",
        defaultValue: "",
        description: "Contents of the CI message received from UMB."
      )
    }

    stages {
        stage("test") {
          steps {
            echo "${params.CI_MESSAGE}"
          }
        }
    }
}
