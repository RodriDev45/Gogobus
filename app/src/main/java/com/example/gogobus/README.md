app/
├─ di/                # Módulos de Hilt/Koin para inyección de dependencias
│   └─ AppModule.kt
│
├─ navigation/        # Gráfico de navegación y destinos
│   └─ AppNavHost.kt
    └─ AuthNavHost.kt
    └─ MainNavHost.kt
│   └─ Destinations.kt
│
├─ core/              # Utilidades, constantes, extensiones, recursos compartidos
│   └─ utils/
│   └─ ui/
│
├─ data/              # Capa de datos
│   ├─ local/         # Room DB / DataStore
│   ├─ remote/        # API / Retrofit
│   ├─ repository/    # Implementaciones de repositorios
│
├─ domain/            # Capa de dominio
│   ├─ model/         # Modelos de negocio
│   ├─ repository/    # Interfaces de repositorios
│   ├─ usecase/       # Casos de uso
│
├─ presentation/      # Capa de UI con MVVM
│   ├─ feature1/
│   │   ├─ Feature1Screen.kt
│   │   ├─ Feature1ViewModel.kt
│   │   └─ components/
│   ├─ feature2/
│   │   ├─ Feature2Screen.kt
│   │   ├─ Feature2ViewModel.kt
│   │   └─ components/
│
├─ MainActivity.kt
└─ GogobusApp.kt           # Punto de entrada con el tema y NavHost
