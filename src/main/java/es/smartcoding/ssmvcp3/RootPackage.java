package es.smartcoding.ssmvcp3;

/*
.   ____          _            __ _ _
/\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
\\/  ___)| |_)| | | | | || (_| |  ) ) ) )
'  |____| .__|_| |_|_| |_\__, | / / / /
=========|_|==============|___/=/_/_/_/
*/

/*
 * Esta interfaz tiene solamente una utilidad, 
 * se utiliza en el atributo basePackageClasses 
 * para escanear componentes de Spring en ese
 * paquete y subpaquetes.
 * 
 * Tiene ventajas sobre basePackages que contiene
 * cadenas, porque STS puede refactorizar el nombre
 * de las clases e interfaces pero un cambio
 * de nombre de paquete tendria que corregirse a
 * mano en el caso del atributo basePackages.
 */
public interface RootPackage {

}
