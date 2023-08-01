 pipeline {
    agent {
      label 'react-frontenddev-node'
    }
    
        stages {
          
        stage('Checkout') {
            steps {
               sh "echo 'cloning the latest application version' "
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'gitlab-meta-credentials', url: 'https://gitlab.com/metabullai1/json-generator-backend.git']])
            }
    }
         stage('Install Python and Pip') {
           steps {
               
              //sh 'sudo yum update -y'
               sh 'sudo apt install -y python3 python3-pip make'
               }
             }
             
        
        stage('Install Django framework') {
           steps {
                //sh 'sudo apt install python3.10-venv -y'
               //sh 'python3 -m venv venv'
              // sh 'source venv/bin/activate'
               //sh 'sudo apt install python3-devel -y' 
               sh 'sudo apt install gcc -y'
               
               sh 'sudo pip3 install -r $WORKSPACE/requirements.txt'
               
               sh 'python3 -m spacy download en_core_web_trf'
               sh 'python3 -m spacy download en_core_web_sm'
               
             
           }
       }  
        stage('Install Django') {
            steps {
                //sh 'source venv/bin/activate'
                sh  'sudo pip3 install django'
                sh 'django-admin --version'

            }
        }
      
       stage('Run Tests') {
            steps {
                // Run Django project tests
                  //sh 'source venv/bin/activate' 
                // sh 'sudo yum upgrade sqlite3'
                sh 'echo skipping tests'
                 //sh 'python3 $WORKSPACE/manage.py test'
                 // sh ' python3 /var/lib/jenkins/workspace/jason-gen/json_gen/main/test.py'
                    }
                } 
                
        stage('Configure URLs') {
            steps {
               // sh 'sed -i "s/from django.conf.urls import patterns, include/from django.urls import path, include/g"/var/lib/jenkins/workspace/json-web/main/urls.py'
          sh "sed -i 's|from django.conf.urls import patterns, include|from django.urls import path, include|g' $WORKSPACE/main/urls.py"

                
            }
      }
        
        stage('Build Artifacts') {
            steps {
                // Generate any required build artifacts (e.g., static files, database migrations)
                //sh 'source venv/bin/activate'
               // sh 'python3 manage.py collectstatic --noinput'
                sh 'python3 $WORKSPACE/manage.py makemigrations'
                sh 'python3  $WORKSPACE/manage.py migrate'
            }
        }
      
        stage('admin login') {
            steps {
                // Run Django project tests
                  //  sh 'python3 manage.py createsuperuser'
                  sh 'python3 $WORKSPACE/manage.py createsuperuser'
                    }
                } 
                
                stage('Enable Apache modules') {
            steps {
                sh 'sudo a2enmod proxy'
                sh 'sudo a2enmod proxy_http'
            }
        }

        stage('Configure Apache reverse proxy') {
            steps {
                sh 'sudo tee /etc/apache2/sites-available/pipeline.conf > /dev/null <<EOT\n' +
                   '<VirtualHost *:80>\n' +
                   '    ServerName 18.119.42.42\n' +
                   '    ProxyRequests Off\n' +
                   '    ProxyPass / http://localhost:8000/\n' +
                   '    ProxyPassReverse / http://localhost:8000/\n' +
                   '</VirtualHost>\n' +
                   'EOT'
            }
        }

        stage('Enable Apache site') {
            steps {
                sh 'sudo a2ensite pipeline.conf'
            }
        }

        stage('Disable default Apache site') {
            steps {
                sh 'sudo a2dissite 000-default.conf'
            }
        }

        stage('Restart Apache') {
            steps {
                sh 'sudo systemctl restart apache2'
            }
        }

        
        stage('run application') {
            steps {
              
                 sh 'python3 $WORKSPACE/manage.py runserver 0.0.0.0:8000 '
                 //sh 'echo python3 manage.py runserver 0.0.0.0:8000'
                
                    }
                } 
      
            
    }
}
