#!/bin/bash
IMAGENAMEX86=influxdb-java-x86
IMAGENAMEARM=influxdb-java-arm



#jar包完成后编译x86版本
docker build -f Dockerfile -t $IMAGENAMEX86:v1.0 .
#编译完成后给镜像打tag
IMAGEID=$(docker images |grep $IMAGENAMEX86 | awk 'NR==1{print}'|awk '{print $3}')
docker tag $IMAGEID registry.cn-hangzhou.aliyuncs.com/needkk/$IMAGENAMEX86:v1.0
#推送镜像到阿里云
docker push registry.cn-hangzhou.aliyuncs.com/needkk/$IMAGENAMEX86:v1.0




#编译arm版本
docker build -f DockerfileArm -t $IMAGENAMEARM:v1.0 .
#编译完成后给镜像打tag
IMAGEIDARM=$(docker images |grep $IMAGENAMEARM | awk 'NR==1{print}'|awk '{print $3}')
docker tag $IMAGEIDARM registry.cn-hangzhou.aliyuncs.com/needkk/$IMAGENAMEARM:v1.0
#推送镜像到阿里云
docker push registry.cn-hangzhou.aliyuncs.com/needkk/$IMAGENAMEARM:v1.0