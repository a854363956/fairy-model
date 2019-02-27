FROM gradle

USER root
RUN apt-get update
RUN apt-get install git -y 


ARG GIT_ADDR='https://github.com/a854363956/fairy-model.git'

USER gradle
RUN  cd /home/gradle \
&& git clone ${GIT_ADDR} \
&& project=`ls` && cd /home/gradle/${project} \
&& gradle build --refresh-dependencies \ 
&& gradle bootRun
