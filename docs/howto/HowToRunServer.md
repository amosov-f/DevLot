# How to run server on local machine

In your IDE, run project with next command line arguments,

`-port 8080 -u $USERNAME -p $PASSWORD -cfg /common.properties /debug.properties`

where $USERNAME is login to your google account and $PASSWORD is it's password

Note, that you must have access to our [dataset in google spreadsheets](https://docs.google.com/spreadsheets/d/1LSpPXxsrMTiFDyBz08OTYh0xRhyou-21f-k1xfGPHPs), and your google authentication must be simple.

# TEMPORARY

In your IDE, run project with next command line arguments,

`-port 8080 -cfg /common.properties /debug.properties`

Save "data" worksheet to `data/spreadsheet/City Block Designer data - data.tsv`

Save "info" worksheet to `data/spreadsheet/City Block Designer data - info.tsv`

See also file "common.properties"