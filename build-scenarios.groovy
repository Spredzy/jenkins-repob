def jobs [:]
def ansibleVersions = ['devel', 'stable-2.8']
def towerVersions = ['devel', '3.5.1']


ansibleVersions.each { ansibleVersion ->
    towerVersions.each { towerVersion ->
        jobs["Tower ${towerVersion} - Ansible ${ansibleversion}"] = {

            node('unit-test-runner') {
                echo "hello world"
            }

        }
    }
}

jobs
