<div align="center">

# MiniFramework

Lightweight Spring IOC Container Practice Framework

</div>

---

## 项目简介

MiniFramework 是一个从零实现的轻量级 Spring IOC 容器框架，旨在通过手写代码的方式深入理解和掌握 Spring 的核心功能。项目支持 XML 配置和注解配置两种方式管理 Bean，未来计划扩展 AOP 功能。

本项目是学习 Spring 框架源码和实践面向对象设计思想的理想案例，通过逐步构建功能模块，帮助开发者真正理解依赖注入、控制反转、Bean 生命周期管理等核心概念。

## 功能特性

### 核心功能
- 🎯 **IOC 容器** - 完整实现控制反转和依赖注入机制
- 📝 **XML 配置支持** - 支持基于 XML 文件的 Bean 配置和管理
- 🏷️ **注解配置支持** - 支持使用 @Component、@Autowired 等注解自动装配 Bean
- 🔄 **Bean 生命周期管理** - 完整的 Bean 创建、初始化、销毁流程
- 📦 **依赖注入** - 支持 Setter 注入和构造器注入

### 设计理念
- 💡 **简洁高效** - 去除冗余功能，专注核心实现
- 🔍 **易于学习** - 代码结构清晰，注释详细，便于理解
- 🧩 **模块化设计** - 功能模块独立，便于扩展和维护
- 📚 **最佳实践** - 遵循 Spring 框架设计原则和编码规范

### 规划中
- 🔮 AOP 功能支持（面向切面编程）
- 🔮 更多注解支持
- 🔮 性能优化和测试用例完善

## 快速开始

### 环境要求

- JDK 8+
- Maven 3.6+

### 构建项目

```bash
# 克隆项目
git clone https://github.com/guyan11/MiniFramework.git
cd MiniFramework

# 编译项目
mvn clean compile

# 运行测试
mvn test

# 打包项目
mvn package
```

## 技术栈

- Java
- Spring Framework
- Maven

## 贡献指南
欢迎对 MiniFramework 感兴趣的开发者贡献代码！无论是 Bug 修复、功能增强还是文档改进，我们都非常欢迎。

## 致谢
感谢 Spring 团队提供了优秀的开源框架，本项目深受 Spring 设计思想的影响。

---

<div align="center">

⭐ 如果这个项目对你有帮助，请给一个 Star！

</div>