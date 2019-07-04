def ciMessage = params.CI_MESSAGE
def buildMetadata = [:]


library identifier: "contra-int-lib@master",
        retriever: modernSCM([$class: 'GitSCMSource',
                              remote: "https://gitlab.sat.engineering.redhat.com/contra/contra-int-lib.git"])

pipeline {
  agent {
    // Ideally, jobs should _not_ run directly on a Jenkins master, but rather on a different agent (slave)
    // This sample is simple enough though and running on master requires less configuration.
    label("master")
  }

  stages {
    stage("Parse 'redhat-container-image.pipeline.running' message") {
      steps {
        script {
          echo "Raw message:\n${ciMessage}"
        }
      }
    }

    stage("Run image tests") {
      steps {
        echo "This would be a stage which executes the actual image tests. Intentionally empty in this sample."
      }
      post {
        always {
          script {
            // report test results to ResultsDB
            def provider = "Red Hat UMB" // change the provider to "Red Hat UMB Stage" for development purposes

            // the following three values need to match the configuration in gating.yaml
            def namespace = "ansible-tower"
            def type = "default"
            def testName = "integration"
            // Status can be 'PASSED', 'FAILED', 'INFO' (soft pass) or 'NEEDS_INSPECTION' (soft fail).
            // See Factory 2.0 CI UMB messages for more info - https://docs.google.com/document/d/16L5odC-B4L6iwb9dp8Ry0Xk5Sc49h9KvTHrG86fdfQM/edit#heading=h.ixgzbhywliel
            def status = "PASSED"

            def brewTaskID = 22498668
            def brewNvr = 'ansible-tower-container-test-42.0'
            def product = 'ansible-tower'

            def msgContent = """
             {
                "category": "${testName}",
                "status": "${status}",
                "ci": {
                    "url": "https://jenkins-cvp-ci.cloud.paas.upshift.redhat.com/",
                    "team": "Ansible Tower",
                    "email": "yguenane@redhat.com",
                    "name": "Your CI name (may or may not be the same as the 'team')"
                },
                "run": {
                    "url": "${BUILD_URL}",
                    "log": "${BUILD_URL}/console"
                },
                "system": {
                    "provider": "openshift",
                    "os": "openshift"
                },
                "artifact": {
                    "nvr": "${brewNvr}",
                    "component": "${product}",
                    "type": "brew-build",
                    "id": "${brewTaskID}",
                    "issuer": "Unknown issuer"
                },
                "type": "${type}",
                "namespace": "${namespace}"
              }"""

            echo "Sending the following message to UMB:\n${msgContent}"

            sendCIMessage(
                messageContent: msgContent,
                messageProperties: '',
                messageType: 'Custom',
                overrides: [topic: "VirtualTopic.eng.ci.brew-build.test.complete"],
                providerName: provider)
          }
        }
      }
    }
  }
}

