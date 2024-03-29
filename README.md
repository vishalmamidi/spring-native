
![Advantages](./assets/Advantages.png)

Execution process when compared to JAR vs Native 

![Alt text](./assets/execution.png)


Memory when scaling

![Scaling](./assets/scaling.png)


## Getting Started

## 1: Using bootBuildImage

```bash
./gradlew bootBuildImage --createdDate "now" --imageName=ghcr.io/vishalmamidi/spring-native:1.1
```

### 2: using custome bootBuildImage task in `build.gradle`
set CONTAINER_IMAGE_NAME & CONTAINER_TAGS as Env's 
#### bash
```bash
export IMAGE_NAME=ghcr.io/vishalmamidi/spring-native IMAGE_TAGS=1.0,latest
```

```Groovy
tasks.named('bootBuildImage') {
	def containerImageName = System.getenv("IMAGE_NAME") ?: "ghcr.io/vishalmamidi/${rootProject.name}"
	def containerTags      = System.getenv("IMAGE_TAGS") ?: "latest"

	// Set Image Properties
	imageName = containerImageName
	tags = (containerTags.split(",") as List).collect { containerImageName + ":" + it }
	builder = "paketobuildpacks/builder:tiny"
	createdDate = "now"
}
```
```bash
./gradlew bootBuildImage
```

### docs for more options while building image
<https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#build-image.customization>


## 2: Using pack cli
<https://buildpacks.io/docs/tools/pack/cli/pack_build>
```bash
brew install buildpacks/tap/pack
```

```bash
./gradlew bootJar
```

```bash
pack build --builder paketobuildpacks/builder:tiny \
   --path build/libs/spring-native-0.0.1-SNAPSHOT.jar \
   --creation-time 'now' \
   --env 'BP_NATIVE_IMAGE=true' \
   --tag 'ghcr.io/vishalmamidi/spring-native:latest' \
   --publish \
   ghcr.io/vishalmamidi/spring-native:0.0.2
```

