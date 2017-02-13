#!/bin/bash
# Jenkins Bootstrap for ACS Kubernetes
set -x #echo on
wget https://raw.githubusercontent.com/mekenthompson/example-voting-app/master/kubectl
chmod +x kubectl
sudo cp kubectl /usr/local/bin/kubectl
sudo mkdir /home/tomcat/.kube
sudo cp igniteconfig /home/tomcat/.kube/config
sudo chown tomcat:tomcat /home/tomcat/.kube/config
sudo usermod -aG docker tomcat
sudo /opt/bitnami/ctlscript.sh restart
set +x #echo off