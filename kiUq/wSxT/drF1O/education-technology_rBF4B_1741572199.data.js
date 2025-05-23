module.exports = function ( eleventyConfig ) {

    // https://www.11ty.dev/docs/config/#template-formats
    eleventyConfig.setTemplateFormats([ "md", "njk" ]);

    return {

        markdownTemplateEngine: "njk",
        dir: {
            output: "./Education/Codeworks"
        }
};