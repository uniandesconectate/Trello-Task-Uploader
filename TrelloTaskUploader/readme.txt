TrelloTaskUploader

Para usar la aplicación se debe:
1. Abrir la clase TrelloTaskUploader y reemplazar los valores de la linea por el appkey y el token de trello

Trello trelloApi = new TrelloImpl("f0232b37cba133351f64c578935cfffc", "bd4a9249044a0ea176b8315fdd6edc95bde3b191edf60547945aa84034571840");

2. poblar el archivo tarjetas.csv de la siguiente manera. Nótese que el campo comment es un texto separado por _ que permite poner varios comentarios. Esto se utiliza para hacer varias entradas de plus for trello para diferentes usuarios en una misma tarjeta.
TEAM,BOARD,CARD,COMMENT,LIST
IP16SGTE,IP16SGTE-201620M2,IP16SGTE-01:Reuniones con profesores,plus! @danielsantamaria6 0/4_--_--_--_--,Por hacer

3. Ejecutar la clase TrelloTaskUploader , no tiene parametros.


 