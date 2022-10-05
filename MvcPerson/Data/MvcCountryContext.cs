using Microsoft.EntityFrameworkCore;

    public class MvcCountryContext : DbContext
    {
        public MvcCountryContext (DbContextOptions<MvcCountryContext> options)
            : base(options)
        {
        }

        public DbSet<MvcPerson.Models.Country> Country { get; set; } = default!;
    }
