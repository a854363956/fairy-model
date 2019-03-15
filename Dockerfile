FROM gradle

USER root
RUN apt-get update
RUN apt-get install git -y 
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ENV GIT_ADDR='https://github.com/a854363956/fairy-model.git'

USER gradle

RUN cd /home/gradle \
&& git clone ${GIT_ADDR} 

ENTRYPOINT cd /home/gradle \
&& project=`ls` \
&& cd /home/gradle/${project} \
&& git pull \
&& gradle build --refresh-dependencies \ 
&& gradle bootRun
