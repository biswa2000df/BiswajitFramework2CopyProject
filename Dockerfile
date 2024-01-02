FROM openjdk:17

WORKDIR /var/lib

COPY BiswajitJAR.jar .

COPY Main_Controller.xlsx .

COPY DataSheet/IshinePortal.xlsx DataSheet/

ENTRYPOINT ["java", "-jar", "BiswajitJAR.jar"]