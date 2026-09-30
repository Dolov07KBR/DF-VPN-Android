<div align="center">
  <img src="docs/assets/logo.png" width="150" alt="DF VPN">
  <h1>DF VPN для Android</h1>
  <p><strong>Современный открытый клиент VPN-подписок на базе Xray и v2rayNG</strong></p>

  [![Android](https://img.shields.io/badge/Android-7%2B-3DDC84?logo=android&logoColor=white)](https://github.com/Dolov07KBR/DF-VPN-Android/releases/latest)
  [![Latest release](https://img.shields.io/github/v/release/Dolov07KBR/DF-VPN-Android?label=release&color=8b5cf6)](https://github.com/Dolov07KBR/DF-VPN-Android/releases/latest)
  [![License](https://img.shields.io/badge/license-GPL--3.0-22d3ee)](LICENSE)
  [![Build](https://github.com/Dolov07KBR/DF-VPN-Android/actions/workflows/df-android.yml/badge.svg)](https://github.com/Dolov07KBR/DF-VPN-Android/actions/workflows/df-android.yml)

  [**Скачать APK**](https://github.com/Dolov07KBR/DF-VPN-Android/releases/latest) · [**Сайт приложения**](https://dolov07kbr.github.io/df-vpn-android.html) · [Сообщить о проблеме](https://github.com/Dolov07KBR/DF-VPN-Android/issues)
</div>

---

## О приложении

**DF VPN** — Android-клиент для импорта и использования совместимых VPN-подписок и отдельных конфигураций. Он работает через системный `VpnService`, не требует root-доступа и подходит для Wi‑Fi, LTE и 5G.

Приложение основано на открытом проекте [v2rayNG](https://github.com/2dust/v2rayNG) и использует возможности Xray-core. DF VPN сохраняет лицензию GPL-3.0, историю upstream и сведения об авторах.

## Возможности

- импорт URL подписки, QR-кода, буфера обмена, локального файла и отдельных proxy URI;
- **VLESS / Reality**, VMess, Trojan, Shadowsocks и другие протоколы, поддерживаемые текущим Xray-core;
- **Smart Route** — проверка серверов и выбор доступного узла с минимальной задержкой;
- отображение расхода, лимита, остатка и срока действия подписки из `subscription-userinfo`;
- DNS через туннель, IPv4 и IPv6;
- маршрутизация и выбор приложений;
- русский интерфейс;
- адаптивные светлая и тёмная темы;
- Android 7+ и проверка на Android 15;
- работа без root-доступа.

> Статистика трафика отображается, когда сервер подписки передаёт стандартный HTTP-заголовок `subscription-userinfo`.

## Установка

1. Откройте раздел [Releases](https://github.com/Dolov07KBR/DF-VPN-Android/releases/latest).
2. Скачайте ARM64 APK для большинства современных смартфонов.
3. Разрешите установку приложений из браузера или файлового менеджера.
4. Установите APK, добавьте подписку и выберите сервер.

Рекомендуется сверить SHA-256 со значением на странице релиза.

## Поддерживаемые форматы

| Категория | Поддержка |
|---|---|
| Подписки | URL, Base64 и совместимые форматы Xray/v2rayNG |
| Импорт | QR, буфер обмена, файл, отдельный URI |
| Протоколы | VLESS, VMess, Trojan, Shadowsocks, SOCKS, HTTP, WireGuard, Hysteria 2* |
| Транспорты | TCP, WebSocket, gRPC и другие поддерживаемые ядром |
| Сети | Wi‑Fi, LTE, 5G, IPv4, IPv6 |

\* Фактическая доступность зависит от версии Xray-core и параметров конкретного сервера.

## Сборка

Android-проект расположен в каталоге `V2rayNG`.

```bash
git clone --recurse-submodules https://github.com/Dolov07KBR/DF-VPN-Android.git
cd DF-VPN-Android/V2rayNG
./gradlew assembleFdroidDebug
```

Для сборки требуются Android SDK/NDK, JDK и нативные библиотеки, указанные в GitHub Actions workflow. Готовая воспроизводимая тестовая сборка выполняется через `.github/workflows/df-android.yml`.

## Конфиденциальность

DF VPN не продаёт данные пользователя и не содержит собственной рекламной аналитики. Сетевой трафик обрабатывается выбранным пользователем сервером. Надёжность и политика сервера зависят от поставщика подписки.

## Лицензия и авторство

DF VPN является производной работой **v2rayNG** и распространяется по лицензии [GPL-3.0](LICENSE).

- Upstream: [2dust/v2rayNG](https://github.com/2dust/v2rayNG)
- Xray-core: [XTLS/Xray-core](https://github.com/XTLS/Xray-core)
- Оригинальное описание upstream сохранено в [UPSTREAM_v2rayNG_README.md](UPSTREAM_v2rayNG_README.md).

DF VPN не является официальным приложением команды v2rayNG/Xray и не связан с Happ.
