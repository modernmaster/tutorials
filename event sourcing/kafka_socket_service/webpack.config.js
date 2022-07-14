const path = require('path');
const fs = require('fs');
const CopyPlugin = require('copy-webpack-plugin');

const nodeModules = {};
fs.readdirSync('node_modules')
    .filter(function(x) {
        return ['.bin'].indexOf(x) === -1;
    })
    .forEach(function(mod) {
        nodeModules[mod] = 'commonjs ' + mod;
    });

const packageData = JSON.parse(fs.readFileSync('package.json').toString());
fs.writeFileSync('VERSION', packageData['version']);

module.exports = function (env) {
    if (!env) env = 'dev';
    const copyPattern = [
        {from: `./VERSION`, to: path.join(__dirname, 'dist', env)},
        {from: './src/config-examples', to: path.join(__dirname, 'dist', env, 'config-examples')}
    ];
    if (env === 'dev') {
        copyPattern.push({from: './src/config-examples/config-dev', to: path.join(__dirname, 'dist', env, 'config')})
    }
    return {
        mode: 'development',
        entry: './src/index.ts',
        target: 'node',
        devtool: 'inline-source-map',
        output: {
            path: path.join(__dirname, 'dist', env),
            filename: 'isb-kafka-consumer.js'
        },
        module: {
            rules: [
                {
                    test: /\.tsx?$/,
                    use: 'ts-loader',
                    exclude: /node_modules/
                }
            ]
        },
        resolve: {
            extensions: [ '.tsx', '.ts', '.js' ]
        },
        externals: nodeModules,
        plugins: [
            new CopyPlugin(copyPattern)
        ]
    };
};

