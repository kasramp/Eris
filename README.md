# Eris ☁️ — Public Weather API & MCP Server

[![Build & Release](https://github.com/kasramp/Eris/actions/workflows/build_and_release.yml/badge.svg)](https://github.com/kasramp/Eris/actions/workflows/build_and_release.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=kasramp_Eris&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=kasramp_Eris)
[![Docker Pulls](https://img.shields.io/docker/pulls/kasramp/eris)](https://hub.docker.com/r/kasramp/eris)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

> A battle-tested, open-source weather API and **public MCP server** - free to use, free to fork, maintained since 2017.

**Live API:** [https://weather-api.madadipouya.com](https://weather-api.madadipouya.com)  
**Swagger Docs:** [https://weather-api.madadipouya.com/swagger-ui/index.html](https://weather-api.madadipouya.com/swagger-ui/index.html)  
**MCP Endpoint:** [https://weather-api.madadipouya.com/mcp](https://weather-api.madadipouya.com/mcp)

---

## What is Eris?

Eris is a Spring Boot weather service that consolidates [OpenWeatherMap](https://openweathermap.org/), [OpenStreetMap](https://openstreetmap.org/), and IP geolocation into a single clean API. It exposes weather data by coordinates or IP address, and since 2026, also serves as a **public MCP (Model Context Protocol) server** that any AI agent can plug into directly.

It's simple by design. But it's built on a foundation that doesn't cut corners.

---

## 🤖 MCP (Use Eris as an AI Tool)

Eris exposes a public MCP server endpoint that works out of the box with any MCP compatible AI agent (Claude, Cursor, and others).

**MCP Server URL:**
```
https://weather-api.madadipouya.com/mcp
```

![demo](https://raw.githubusercontent.com/kasramp/Eris/master/assets/Claude AI Integration.mp4)

### Connect to Claude Desktop

Add the following to your `claude_desktop_config.json`:

```json
{
  "mcpServers": {
    "eris-weather": {
      "type": "sse",
      "url": "https://weather-api.madadipouya.com/mcp"
    }
  }
}
```

### Available MCP Tools

| Tool | Description |
|------|-------------|
| `get_current_weather_by_latitude_and_longitude` | Get weather for a specific location by coordinates |
| `get_current_weather_by_ip_address` | Get weather based on caller's IP address |

No API key required. No sign-up. Just point and use.

> For testing MCP locally with tools like `mcp-cli`, see [`mcp/README.md`](mcp/README.md).

---

## ✨ Why Eris?

This project has been quietly doing things right for nearly a decade. Here's what's under the hood:

| Area | Detail                                                                                                            |
|------|-------------------------------------------------------------------------------------------------------------------|
| **Test Coverage** | 100% — enforced via SonarCloud on every build                                                                     |
| **Code Quality** | Zero code smells, zero duplications. SonarCloud Quality Gate always green                                         |
| **CI/CD Pipeline** | Auto builds, auto releases to GitHub Releases, auto pushes Docker image to Docker Hub                             |
| **Auto-deployment** | Merges to `master` trigger live deployment via [Adnanh Webhook](https://github.com/adnanh/webhook) + Docker Swarm |
| **Security Scanning** | NVD dependency vulnerability check runs on every merge to `develop`                                               |
| **Versioned Releases** | Automatic release notes and GitHub Release artifacts on every tag                                                 |
| **MCP Ready** | Public MCP server endpoint, usable by any AI agent without setup                                                  |
| **Swagger Docs** | Full interactive API documentation at `/swagger-ui/index.html`                                                    |
| **Clustering** | Docker Swarm support with provided `docker-compose-swarm.yml`                                                     |
| **Heroku** | One click deployment button                                                                                       |
| **Longevity** | Maintained from Spring Boot 1 → 3.5 over 8 years                                                                  |

---

## 🚀 Quick Start

The fastest way to get weather data, no account needed:

```bash
# By coordinates
$ curl "https://weather-api.madadipouya.com/v1/weather/current?lat=51.5074&lon=-0.1278"

# By IP address (uses caller's IP)
$ curl "https://weather-api.madadipouya.com/v1/weather/currentbyip"
```

Full API reference with live examples: [Swagger UI →](https://weather-api.madadipouya.com/swagger-ui/index.html)

---

## API Endpoints

| Endpoint | Parameters | Description |
|----------|-----------|-------------|
| `GET /v1/weather/current` | `lat`, `lon`, `fahrenheit` (optional) | Weather by coordinates |
| `GET /v1/weather/currentbyip` | `fahrenheit` (optional) | Weather by caller IP |

For full request/response examples, see the [GitHub Pages documentation](https://eris.madadipouya.com).

---

## 🐳 Run Your Own Instance

### Docker (recommended)

```bash
$ docker run -p 8080:8080 \
  -e OPENWEATHERMAP_API_KEY=your-api-key \
  -e ACTUATOR_USERNAME=your-username \
  -e ACTUATOR_PASSWORD=your-password \
  kasramp/eris:latest
```

Images are automatically built and pushed to [Docker Hub](https://hub.docker.com/r/kasramp/eris) on every release.

### Docker Swarm (cluster mode)

```bash
$ export OPENWEATHERMAP_API_KEY=your-api-key
$ export ACTUATOR_USERNAME=your-username
$ export ACTUATOR_PASSWORD=your-password
$ docker stack deploy --with-registry-auth -c docker-compose-swarm.yml eris_stack
```

### Heroku (one-click)

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/kasramp/Eris)

### Local Development

Requires JDK 25 and Maven.

```bash
$ git clone https://github.com/kasramp/Eris.git
$ cd Eris
$ mvn spring-boot:run
```

### Build Über-JAR

```bash
$ mvn clean install
$ cd target
$ java -jar eris-*.jar
```

---

## 🔧 CI/CD & Engineering Highlights

```
Pull Request → develop
    └── SonarCloud analysis (quality gate + 100% coverage check)
    └── NVD dependency vulnerability scan

Merge to master
    └── GitHub Actions build
    └── Docker image pushed to Docker Hub  →  kasramp/eris:latest
    └── GitHub Release created with auto-generated release notes
    └── Adnanh Webhook triggers live redeployment on weather-api.madadipouya.com
```

The live instance runs in Docker Swarm with multiple replicas for availability.

---

## Tech Stack

- **Java 25** / **Spring Boot 3.5**
- **Maven**
- **Docker** / **Docker Swarm**
- **SonarCloud** (quality + coverage)
- **GitHub Actions** (CI/CD)
- **Spring MCP** (MCP server)
- **Springdoc / Swagger UI** (API docs)

Data sources: [OpenWeatherMap](https://openweathermap.org/) · [OpenStreetMap](https://openstreetmap.org/) · [IP-API](http://ip-api.com/)

---

## Contributing

All contributions are welcome including bug reports, pull requests, and ideas. Please open an issue first for significant changes.

---

## License

Eris is free software licensed under the [GNU General Public License v3](https://www.gnu.org/licenses/gpl-3.0).

© 2017–2026 [Kasra Madadipouya](mailto:kasra@madadipouya.com)