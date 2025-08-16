# 🏦 Proyecto: Cuenta Bancaria

## 📌 Descripción
Este proyecto implementa un modelo orientado a objetos de una **cuenta bancaria**, incluyendo una clase base `Cuenta` y dos clases derivadas: `CuentaAhorros` y `CuentaCorriente`.  
El sistema gestiona operaciones como consignaciones, retiros, cálculo de intereses, extractos mensuales y control de comisiones.

---

## 📂 Estructura de Clases

### 🔹 Clase `Cuenta`
- **Atributos (protegidos)**  
  - `saldo: float`  
  - `numConsignaciones: int` (inicia en 0)  
  - `numRetiros: int` (inicia en 0)  
  - `tasaAnual: float`  
  - `comisionMensual: float` (inicia en 0)  

- **Métodos**  
  - `consignar(float cantidad)`  
  - `retirar(float cantidad)`  
  - `calcularInteresMensual()`  
  - `extractoMensual()`  
  - `imprimir(): string`  

---

### 🔹 Clase `CuentaAhorros` (hereda de `Cuenta`)
- **Atributos**  
  - `activa: boolean` (inactiva si el saldo < 10000)  

- **Métodos redefinidos**  
  - `consignar(float cantidad)` → Solo si está activa  
  - `retirar(float cantidad)` → Solo si está activa  
  - `extractoMensual()` → Cobra comisión extra si hay más de 4 retiros  

- **Método adicional**  
  - `imprimir(): string` → Retorna saldo, comisión mensual y total de transacciones  

---

### 🔹 Clase `CuentaCorriente` (hereda de `Cuenta`)
- **Atributos**  
  - `sobregiro: float` (inicia en 0)  

- **Métodos redefinidos**  
  - `retirar(float cantidad)` → Permite sobregiro  
  - `consignar(float cantidad)` → Reduce sobregiro si existe  
  - `extractoMensual()`  

- **Método adicional**  
  - `imprimir(): string` → Retorna saldo, comisión mensual, transacciones y sobregiro  

---

## 📊 Diagrama de Clases (Mermaid)

```mermaid
%% Notación: # = protegido, + = público
classDiagram
    direction TB

    class Cuenta {
        # float saldo
        # int numConsignaciones = 0
        # int numRetiros = 0
        # float tasaAnual
        # float comisionMensual = 0
        + Cuenta(float saldo, float tasaAnual)
        + consignar(float cantidad) void
        + retirar(float cantidad) void
        + calcularInteresMensual() void
        + extractoMensual() void
        + imprimir() string
    }

    class CuentaAhorros {
        # boolean activa
        + CuentaAhorros(float saldo, float tasaAnual)
        + consignar(float cantidad) void
        + retirar(float cantidad) void
        + extractoMensual() void
        + imprimir() string
        + isActiva() boolean
    }

    class CuentaCorriente {
        # float sobregiro = 0
        + CuentaCorriente(float saldo, float tasaAnual)
        + consignar(float cantidad) void
        + retirar(float cantidad) void
        + extractoMensual() void
        + imprimir() string
    }

    Cuenta <|-- CuentaAhorros
    Cuenta <|-- CuentaCorriente

    %% Reglas de negocio
    note left of Cuenta
      • retirar: no permite superar el saldo.
      • extractoMensual: saldo -= comisionMensual;
        luego calcularInteresMensual().
    end note

    note right of CuentaAhorros
      • activa si saldo >= 10000; si no, inactiva.
      • consignar/retirar: sólo si activa (invoca heredado).
      • extractoMensual: si numRetiros > 4,
        comisionMensual += 1000 por retiro extra.
      • Tras el extracto, recalcular 'activa'.
    end note

    note right of CuentaCorriente
      • retirar: puede exceder el saldo; exceso -> sobregiro.
      • consignar: invoca heredado; si hay sobregiro,
        la consignación lo reduce antes que aumentar el saldo.
      • extractoMensual: igual que en Cuenta.
    end note
