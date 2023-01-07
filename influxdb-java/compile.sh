#!/bin/bash
IMAGENAMEX86=influxdb-java-x86
IMAGENAMEARM=influxdb-java-arm



#jar����ɺ����x86�汾
docker build -f Dockerfile -t $IMAGENAMEX86:v1.0 .
#������ɺ�������tag
IMAGEID=$(docker images |grep $IMAGENAMEX86 | awk 'NR==1{print}'|awk '{print $3}')
docker tag $IMAGEID registry.cn-hangzhou.aliyuncs.com/needkk/$IMAGENAMEX86:v1.0
#���;��񵽰�����
docker push registry.cn-hangzhou.aliyuncs.com/needkk/$IMAGENAMEX86:v1.0




#����arm�汾
docker build -f DockerfileArm -t $IMAGENAMEARM:v1.0 .
#������ɺ�������tag
IMAGEIDARM=$(docker images |grep $IMAGENAMEARM | awk 'NR==1{print}'|awk '{print $3}')
docker tag $IMAGEIDARM registry.cn-hangzhou.aliyuncs.com/needkk/$IMAGENAMEARM:v1.0
#���;��񵽰�����
docker push registry.cn-hangzhou.aliyuncs.com/needkk/$IMAGENAMEARM:v1.0