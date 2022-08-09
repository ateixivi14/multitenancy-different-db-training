

# Database per tenant
A tenant (organization) has its own database. 
Each time a new tenant is added to the system, a new database is generated for the user.
## Multi-Tenant Database advantages:
- The highest level of tenant isolation and data security 
- Data remains invisible to other users
- Cloud environments can scale out and scale up because tenants can be spread over multiple servers.
## Multi-Tenant Database disadvantages:
- Potentially more servers to patch and maintain,
- As the number of tenants grows, there will be more databases being created,
- Maintain a registry of tenant-db mappings code can lead to added complexity,
- Application data shared by all tenants (i.e., dictionaries) in this type of multi-tenant system must be duplicated in each database or need to be extracted to another one that will be used by all tenants.