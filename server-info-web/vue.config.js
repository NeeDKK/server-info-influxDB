module.exports = {
    outputDir: 'dist',   //build输出目录
    assetsDir: 'assets', //静态资源目录（js, css, img）
    lintOnSave: false, //是否开启eslint
    devServer: {
        host: "localhost",
        port: '8080',
        https: false,
        hotOnly: false,
        proxy: {
            '/api/java': {
                target: 'http://1.15.106.156:8091',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/api/java': ''
                }
            },
            '/api/go': {
                target: 'http://1.15.106.156:8090',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/api/go': ''
                }
            }
        },
    },
    runtimeCompiler: true,
};

