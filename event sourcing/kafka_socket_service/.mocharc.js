module.exports = {
	"spec": "test/**/*.spec.ts",
	"require": [
		"ts-node/register",
		"@babel/register",
		"source-map-support/register"
	],
	"reporter": "spec",
	"exit": true
}
