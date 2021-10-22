# Package Structure

Within our project's root package, packages are grouped by feature. The only exception is a core module
with classes that are shared across the application. Within a feature package, information is split by responsibility.
The ui code will go into one package, while the domain information in another - which is then split by use cases,
repositories, and model classes.