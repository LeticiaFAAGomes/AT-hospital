# Build (stage): compila o projeto usando Maven + JDK
FROM maven:3.9-eclipse-temurin-21 AS builder

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom para aproveitar cache de dependências
COPY pom.xml .

# Baixa dependências para o cache do Maven
RUN mvn dependency:go-offline

# Copia o código-fonte para compilar
COPY src ./src

# Compila e gera o JAR em target/
RUN mvn clean package -DskipTests


# Runtime (stage): imagem menor com apenas JRE
FROM eclipse-temurin:21-jre

# Diretório de trabalho na imagem final
WORKDIR /app

# Copia o JAR gerado no builder e renomeia para app.jar (nome fixo para execução)
# Copia qualquer JAR gerado em target/ e renomeia para `app.jar` para execução
# Atenção: garanta que o packaging do projeto seja `jar` (não `war`) ou configure
# `finalName` no `pom.xml` para padronizar o nome do artefato.
COPY --from=builder /app/target/*.jar app.jar

# Indica a porta que a aplicação usa
EXPOSE 8080

# Checa periodicamente se a aplicação está respondendo
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8080/ || exit 1

# Comando padrão ao iniciar o container
ENTRYPOINT ["java", "-jar", "app.jar"]