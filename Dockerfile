FROM armdocker.rnd.ericsson.se/rhel7:latest

RUN echo -e "[rhelrepo]\nname=RHEL7\nbaseurl=http://yum.linux.ericsson.se/repos/rhel-x86_64-server-7 \nenabled=1\ngpgcheck=0" >> /etc/yum.repos.d/rhel7.repo


RUN yum clean all && \
    yum install -y nc && \
    yum install -y zip unzip curl wyum install -y zip -y software-properties-commonget ssh iproute2 iputils-ping vim && \
    su -c "yum install -y java-1.8.0-openjdk"
    
COPY ./target/ocwf_micro.jar ocwf_micro.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ocwf_micro.jar"]