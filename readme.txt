Directory Scanner
Описание
Требуется написать программу, которая будет сканировать заданную директорию, и копировать файлы, удовлетворяющие определенному условию в выходную директорию. При этом сканер должен поддерживать следующий набор опций комнад:
	- inputDir  	- сканируемая директория
	- outputDir	- выходная директория 
	- mask	- маска для файлов, которые должны копироваться сканером
	- waitInterval 	- интервал, с которым сканер будет проверять заданную директорию
	- includeSubfolders	- включать или не включать обработку поддиректорий (если включать, то сканер должен сохранять структуру поддиректорий при копировании в выходную директорию)
	- autoDelete	- удалять или не удалять файлы после копирования в сканируемой директории
Программа должна поддерживать многопоточность и работать под командной строкой приблизительно со следующим синтаксисом:
c:>java my.app.DirectoryScannerConsole
>scan –input “C:/test/in” –output “C:/test/out” –mask “*test*.xml” –waitInterval 60000 – includeSubfolders false –autoDelete true
>scan –input “C:/test1/in” –output “C:/test1/out” –mask “*test1*.xml” –waitInterval 90000 – includeSubfolders true –autoDelete false
…
>exit
c:>
Где после запуска выводится приглашение для ввода команд. В нашем случае мы имеем две команды: scan и exit. Где scan запускает сканер в отдельном потоке с заданными параметрами. И exit корректно останавливает работу всех запущенных сканеров и завершает работу программы.
Каждый сканер должен писать лог информацию о процессе сканирования в отдельный файл. 
Программа должна корректно обрабатывать неправильно введенные параметры, например на следующую команду программа должна ответить следующим образом:
>scan –input “C:/test/in?” –output “C:/test/out” –mask “\x13*test*.xml” –waitInterval -60000 – includeSubfolders истина –autoDelete true
Error: Scanner was not started due to following reasons:
   Illegal symbols in input path: C:/test/in?
   Illegal symbols in mask: \x13*test*.xml
   Incorrect value for waitInterval: -60000 (must be positive int value)
   Invalid value for includeSubfolders: истина (must be true or false)
>
Требования к реализации

1. Чистый и целесообразно документированый код
2. Использовать log4j для логирования.
Приветствуется
Улучшения в функциональность сканера (например, добавление поддержки команды для остановки выбранного сканера).

RESULT:

Program scan the specified directory, 
and copies the files that meet a certain condition 
in the output directory.

Available 5 commands:

scan    Scan the specified directory and copies files that meet a certain condition in to the output directory
        Parameters:
        -inputDir            Input Directory (required)",
        -outputDir           Output Directory (required)",
	-mask                Mask for files to be copied (required)",
        -waitInterval        Interval with which the scanner scans the specified directory (required)",
        -includeSubFolders   Or may not include the processing of subdirectories (not required)",
        -autoDelete          Delete or not delete, the files after copying (not required)",
        Example: scan -inputDir c:/test/in -outputDir c:/test/out -mask *.* -waitInterval 30000 -includeSubFolders true -autoDelete false"

info    Provides information about running processes.         

help    Provides Help information

stop    Stop scan process by Id.",
        parameter:   id of running thread
 	Example: stop 1252356

exit    Quits the DirectoryScanner program."
