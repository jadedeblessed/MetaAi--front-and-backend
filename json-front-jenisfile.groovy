pipeline {
    agent any
    
    tools {
    nodejs "node"
    }
    stages {
        
        stage('Check version') {
            steps {
                sh 'sudo yum update'
                sh 'sudo yum install -y gcc-c++ make'
                sh 'curl -sL https://rpm.nodesource.com/setup_12.x | sudo bash -'
                  //sh 'curl -sL https://rpm.nodesource.com/setup_14.x | sudo bash -'
                
                sh 'sudo yum install -y nodejs'
                sh 'node --version'
//npm --version'
               // sh 'sudo yum module install nodejs:18/ common'
              // sh "npm version"  
             }
          }
         
        stage('Checkout') {
            steps {
               sh "echo 'cloning the latest application version' "
                   checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'gitlab-meta-credentials', url: 'https://gitlab.com/metabullai1/metabullai-web-frontend.git']])
              
                
            }
            
    }
          stage('Install Dependencies') {
      steps {
        // Clean up any previously installed dependencies
        
        //sh 'npm ci --cache .npm --prefer-offline'

        // Install dependencies
        sh 'npm install'
      }
    }

    stage('Run') {
      steps {
        // Run tests
       // sh 'npm test'
         sh 'npm start'
      }
    }