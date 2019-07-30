def jobs = [:]
def ansibleVersions = ['devel', 'stable-2.8']
def towerVersions = ['devel', '3.5.1']


ansibleVersions.each { ansibleVersion ->
    towerVersions.each { towerVersion ->
        jobs["Tower ${towerVersion} - Ansible ${ansibleVersion}"] = {

            node('jenkins-jnlp-agent') {
                // build(
                //   job: 'qe-sandbox/spredzy/dummy'
                // )
                load('lala.groovy')
            }

        }
    }
}

jobs
