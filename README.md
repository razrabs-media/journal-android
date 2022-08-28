# Разрабы - Android-приложение

## Стек
- Язык: Kotlin
- DI: Koin Annotations
- Database: Room
- Networking: GraphQL
- UI: Compose, Accompanist
- Presentation-level pattern: MVI
- Multi-threading: Coroutines

На данный момент нет переменных окружения

## Требования к окружению
- Android Studio
- Java 11

## Интересные всякости
- После изменения flavor требуется clean потому что Koin не подтирает сгенерированные файлы. В итоге получаем duplicate classes
- На каждый функциональный тип подгружаемых изображений создаем свой ImageLoader, наследующийся от BaseImageLoader
- 'debug' модуль требуется для флагов и экранов для дебага
- dev flavor лезет в dev бэк, prod лезет в prod бэк

## Архитектура
- Бьем по фичам функционал
- По возможности фича не наследует фичу за исключением вложенности или общих модулей типа 'feature_auth'

## Текущие проблемы
- Не настроен R8
- Не вставлена метрика
- Не была выведена сборка во внутреннее тестирование Google Play
