const express = require('express')
const app = express()

const baseDir = `${__dirname}/build/`
console.log(baseDir)

app.use(express.static(baseDir))
app.get('*', (request, response) => { 
    response.sendFile('index.html', {
        root: baseDir
    })
})

app.listen(4000, () => { 
    console.log("Servidor subiu com sucesso")
})

