
pipelineJob("cvp-sample-trigger-external-image-tests") {
  definition {

    parameters {
      stringParam("CI_MESSAGE", "", "Contents of the CI message received from UMB.")
      stringParam("GIT_REPO", "https://github.com/Spredzy/jenkins-repob", "Git repo with the Jenkinsfile.")
      stringParam("GIT_BRANCH", "master", "Git branch to checkout.")
    }

    triggers {
      // This registers a new Jenkins build trigger, which triggers the job when new message is sent to the
      // configured topic (VirtualTopic.eng.ci.redhat-container-image.pipeline.running)
      // -- e.g. when a CVP pipeline execution has started.
      // Jenkins instance running this job needs to have the `jms-messaging-plugin` and `redhat-ci-plugin` installed.
      //
      // Please follow instructions from https://docs.engineering.redhat.com/display/CentralCI/Installing+and+Updating+the+Red+Hat+CI+Plugin
      // or you can also download the version directly
      // from http://artifactory.rhev-ci-vms.eng.rdu2.redhat.com:8081/artifactory/ci-ops-releases-local/com/redhat/jenkins/plugins/redhat-ci-plugin/
      ciBuildTrigger {
        providerData {
          activeMQSubscriberProviderData {
            // UMB provider name - for development purposes this should be changed to 'Red Hat UMB Stage'.
            // The "Red Hat UMB" provider is configured out-of-the-box by the redhat-ci-plugin
            name("Red Hat UMB")
            overrides {
              // The topic name needs to be unique for every job listening to the UMB (note the UUID in the middle).
              // When reusing (copy-pasting) this configuration, make sure to change the UUID to a different one, or use
              // different unique string
              topic("Consumer.rh-jenkins-ci-plugin.doingsomething.VirtualTopic.eng.brew.task.open")
            }
            //
            // Message Checks
            //   See https://datagrepper.engineering.redhat.com/raw?topic=/topic/VirtualTopic.eng.ci.redhat-container-image.pipeline.running&delta=32400
            //   for examples of message content for various images.
            //checks {
            //  msgCheck {
            //    // The field attribute supports JSONPath notation
            //    field('$.artifact.type')
            //    // the expectedValue supports regex.
            //    expectedValue("cvp")
            //  }
            //  msgCheck {
            //    field('$.artifact.nvr')
            //    // Update this for your particular image.
            //    expectedValue("^my-image-name.*")
            //  }
            //  // Optional
            //  //msgCheck {
            //    //field('$.artifact.component')
            //      // Update this for your particular product.
            //      // You can get this info from your CVP Product Master Jenkins URL
            //      // e.g. https://jenkins-cvp-redhatopenshiftcontainerplatform.cloud.paas.upshift.redhat.com/
            //      //                      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            //    //expectedValue("cvp-redhatopenshiftcontainerplatform")
            //  //}
            //}
          }
        }
        noSquash(true)
      }
    }

    cpsScm {
      scm {
        git {
          remote {
            url('${GIT_REPO}')
          }
          branch('${GIT_BRANCH}')
        }
      }
      scriptPath("tata.groovy")
      lightweight(false)
    }
  }
}
