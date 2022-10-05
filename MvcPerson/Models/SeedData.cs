using Microsoft.EntityFrameworkCore;

namespace MvcPerson.Models
{
    public static class SeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MvcCountryContext(
                serviceProvider.GetRequiredService<
                    DbContextOptions<MvcCountryContext>>()))
            {
                // Look for any movies.
                if (context.Country.Any())
                {
                    return;   // DB has been seeded
                }

                context.Country.AddRange(
                    new Country
                    {
                        Name = "Spain"
                    },

                    new Country
                    {
                        Name = "France"
                    },
                    new Country
                    {
                        Name = "England"
                    },
                    new Country
                    {
                        Name = "Germany"
                    },
                    new Country
                    {
                        Name = "Italy"
                    },
                    new Country
                    {
                        Name = "Norway"
                    },
                    new Country
                    {
                        Name = "Finland"
                    }
                );
                context.SaveChanges();
            }
        }
    }
}